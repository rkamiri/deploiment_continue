package ecommercGesture.application.userQueriesCommandsEvents.queries;

import kernel.Query;

public class RetrieveUserById implements Query {
    public final int userId;

    public RetrieveUserById(int userId) {
        this.userId = userId;
    }
}
