package responses.downloadstation.task;

import java.util.List;

public record DsmListTasksResponse(
        int offset,
        List<Task> tasks,
        int total
) {
    public record Task(
            Additional additional,
            String id,
            long size,
            String status,
            String title,
            String type,
            String username
    ) {
    }

    public record Additional(Detail detail, Transfer transfer) {
    }

    public record Transfer(int downloaded_pieces, long size_downloaded, long size_uploaded, long speed_download,
                           long speed_upload) {
    }

    public record Detail(
            long completed_time,
            int connected_leechers,
            int connected_peers,
            int connected_seeders,
            long create_time,
            String destination,
            long seedelapsed,
            long started_time,
            int total_peers,
            int total_pieces,
            String unzip_password,
            String uri,
            long waiting_seconds
    ) {
    }
}




