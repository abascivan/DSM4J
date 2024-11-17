package clients;

import exeptions.DsmLoginException;
import requests.DsmAuth;
import requests.downloadstation.task.DsmCreateTaskRequest;
import requests.downloadstation.task.DsmDeleteTaskRequest;
import requests.downloadstation.task.DsmListTasksRequest;
import requests.filestation.login.DsmLoginRequest;
import requests.filestation.login.DsmLogoutRequest;
import responses.Response;
import responses.filestation.DsmSimpleResponse;
import responses.filestation.login.DsmLoginResponse;

import java.util.Optional;

public class DsmDownloadStationClient {

    private DsmAuth dsmAuth;

    public DsmDownloadStationClient(DsmAuth dsmAuth) {
        this.dsmAuth = dsmAuth;
    }

    public DsmAuth getDsmAuth() {
        return dsmAuth;
    }

    /**
     * login to the server
     * @param auth information about the server and user to connect
     * @return DsmDownloadStationClient
     */
    public static DsmDownloadStationClient login(DsmAuth auth) {

        Response<DsmLoginResponse> response = new DsmLoginRequest(Optional.ofNullable(auth).orElseThrow(() -> new DsmLoginException("DsmAuth can't be null"))).call();

        response = Optional.ofNullable(response).orElseThrow(() -> new DsmLoginException("An error occurred while trying to connect"));

        if(!response.isSuccess()) {
            throw new DsmLoginException(response.getError());
        }
        auth = auth.setSid(response.getData().getSid());

        return new DsmDownloadStationClient(auth);
    }

    /**
     * logout from server
     * @return boolean
     */
    public boolean logout() {
        Response<DsmSimpleResponse> response = new DsmLogoutRequest(
            Optional.ofNullable(this.dsmAuth).orElseThrow(() -> new DsmLoginException("You are already logged out"))
        )
            .call();

        this.dsmAuth = null;
        response = Optional.ofNullable(response).orElseThrow(() -> new DsmLoginException("The request generates no response"));

        if(!response.isSuccess()) {
            throw new DsmLoginException(response.getError());
        }

        return response.isSuccess();
    }

    /**
     * delete a download task
     * @param taskId id of the task to delete
     * @return DsmDeleteTaskRequest which can be used to start the deletion
     */
    public DsmDeleteTaskRequest deleteTask(String taskId) {
        return new DsmDeleteTaskRequest(dsmAuth)
            .addTaskId(taskId);
    }

    /**
     * List all download tasks
     * @param additional which information to include in the response. Possible values are "detail", "transfer", "file", "tracker"
     * @return DsmListTasksRequest which can be used to start the request
     */
    public DsmListTasksRequest listTasks(String additional) {
        return new DsmListTasksRequest(dsmAuth)
            .addAdditional(additional);
    }

    /**
     * create a download task
     * @param uri the uri of the task
     * @return DsmCreateTaskRequest which can be used to start the creation
     */
    public DsmCreateTaskRequest createTask(String uri) {
        return new DsmCreateTaskRequest(dsmAuth)
            .withUri(uri);
    }

    public void task(String id) {
        //new DsmTaskRequest();
    }
}
