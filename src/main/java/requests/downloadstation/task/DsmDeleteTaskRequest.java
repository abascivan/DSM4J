package requests.downloadstation.task;

import com.fasterxml.jackson.core.type.TypeReference;
import exeptions.DsmDeleteException;
import requests.DsmAbstractRequest;
import requests.DsmAuth;
import responses.Response;
import responses.downloadstation.task.DsmDeleteTaskResponse;

import java.util.List;
import java.util.Optional;

public class DsmDeleteTaskRequest extends DsmAbstractRequest<List<DsmDeleteTaskResponse>> {

    private String taskId;

    public DsmDeleteTaskRequest(DsmAuth auth) {
        super(auth);
        this.apiName = "SYNO.DownloadStation.Task";
        this.version = 1;
        this.method = "delete";
        this.path = "/webapi/DownloadStation/task.cgi";
    }

    @Override
    protected TypeReference getClassForMapper() {
        return new TypeReference<Response<List<DsmDeleteTaskResponse>>>() {
        };
    }

    @Override
    public Response<List<DsmDeleteTaskResponse>> call() {
        addParameter("id", Optional.ofNullable(this.taskId).orElseThrow(() -> new DsmDeleteException("the task id can not be null")));
        return super.call();
    }

    public DsmDeleteTaskRequest addTaskId(String taskId) {
        this.taskId = taskId;
        return this;
    }
}
