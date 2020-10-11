package com.example.ankit.advisr.utils;

public class Constants {

    public static class Routes {

        // Routes For UserController
        public static final String LOGIN = "/login";
        public static final String LOGOUT = "/logout";
        public static final String SIGN_UP = "/signUp";
        public static final String GET_CURRENT_USER = "/getCurrentUser";

        // Routes for QuestionController
        public static final String GET_QUESTIONS = "/getQuestions";
        public static final String ASK_QUESTION = "/askQuestion";
        public static final String GET_PRIORITY_QUESTIONS = "/getPriorityQuestions";
        public static final String GET_QUESTIONS_BY_DESCRIPTION_AND_HEADING = "/getQueByHeadingAndDescription";
        public static final String GET_QUESTION_BY_ID = "/getQueById";

        // Routes for ConnectionController
        public static final String GET_CONNECTIONS = "/getConnections";
        public static final String GET_SUGGESTIONS = "/getSuggestions";
        public static final String GET_CONNECTION_REQUESTS = "/getConnectionRequests";
        public static final String REQUEST_CONNECTION = "/requestConnection";
        public static final String GET_MY_REQUESTS = "/getMyRequests";
        public static final String ACCEPT_CONNECTION = "/acceptConnectionRequest";
    }

    public static class User {
        public static final String EMAIL = "email";
        public static final String PASSWORD = "password";
    }

    public static class Session {
        public static final String USER_ID = "userId";
    }

    // General Constants
    public static final String APPLICATION_JSON = "application/json; charset=utf-8";

}
