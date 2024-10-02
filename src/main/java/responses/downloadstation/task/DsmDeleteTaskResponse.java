package responses.downloadstation.task;

public class DsmDeleteTaskResponse {

    private Integer error;
    private String id;

    public DsmDeleteTaskResponse(Integer error, String id) {
        this.error = error;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getError() {
        return error;
    }

    public void setError(Integer error) {
        this.error = error;
    }
}
