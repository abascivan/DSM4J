package requests.downloadstation.task;

import com.fasterxml.jackson.core.type.TypeReference;
import exeptions.DsmDeleteException;
import requests.DsmAbstractRequest;
import requests.DsmAuth;
import responses.Response;
import responses.filestation.DsmSimpleResponse;

import java.util.Optional;

public class DsmCreateTaskRequest extends DsmAbstractRequest<DsmSimpleResponse> {

    private String uri;

    public DsmCreateTaskRequest(DsmAuth auth) {
        super(auth);
        this.apiName = "SYNO.DownloadStation.Task";
        this.version = 1;
        this.method = "create";
        this.path = "/webapi/DownloadStation/task.cgi";
    }

    @Override
    protected TypeReference getClassForMapper() {
        return new TypeReference<Response<DsmSimpleResponse>>() {
        };
    }

    @Override
    public Response<DsmSimpleResponse> call() {
        addParameter("uri", Optional.ofNullable(this.uri).orElseThrow(() -> new DsmDeleteException("the task id can not be null")));
        return super.call();
    }

    public DsmCreateTaskRequest withUri(String uri) {
        this.uri = uri;
        return this;
    }
}
