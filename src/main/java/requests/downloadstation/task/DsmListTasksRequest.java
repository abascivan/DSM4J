package requests.downloadstation.task;

import com.fasterxml.jackson.core.type.TypeReference;
import exeptions.DsmDeleteException;
import requests.DsmAbstractRequest;
import requests.DsmAuth;
import responses.Response;
import responses.downloadstation.task.DsmListTasksResponse;

import java.util.Optional;

public class DsmListTasksRequest extends DsmAbstractRequest<DsmListTasksResponse> {

    private String additional;

    public DsmListTasksRequest(DsmAuth auth) {
        super(auth);
        this.apiName = "SYNO.DownloadStation.Task";
        this.version = 1;
        this.method = "list";
        this.path = "/webapi/DownloadStation/task.cgi";
    }

    @Override
    protected TypeReference getClassForMapper() {
        return new TypeReference<Response<DsmListTasksResponse>>() {
        };
    }

    @Override
    public Response<DsmListTasksResponse> call() {
        addParameter("additional", Optional.ofNullable(this.additional).orElseThrow(() -> new DsmDeleteException("the additional can not be null")));
        return super.call();
    }

    public DsmListTasksRequest addAdditional(String additional) {
        this.additional = additional;
        return this;
    }
}
