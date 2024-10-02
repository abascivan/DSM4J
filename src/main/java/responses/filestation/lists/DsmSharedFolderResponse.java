package responses.filestation.lists;

import responses.filestation.DsmResponseFields;

import java.util.List;

public record DsmSharedFolderResponse(Integer total, Integer offset, List<DsmResponseFields.Share> shares) {}
