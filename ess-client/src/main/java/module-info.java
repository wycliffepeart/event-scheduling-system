module org.ess {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires java.sql;
    requires static lombok;
    requires retrofit2;
    requires com.google.gson;
    requires retrofit2.converter.gson;
    requires org.apache.logging.log4j;
    requires org.jetbrains.annotations;
    requires stomp.websocket;
    requires spring.messaging;
    requires spring.websocket;
    requires com.fasterxml.jackson.databind;
    requires java.validation;
    requires javafaker;
    requires io.reactivex.rxjava3;
    requires okhttp3;

    opens org.ess to javafx.fxml, lombok, com.google.gson;
    opens org.ess.module.user.controller to javafx.fxml;
    opens org.ess.app.websocket to com.fasterxml.jackson.databind;
    opens org.ess.app.controller to com.google.gson, javafx.fxml, lombok;
    opens org.ess.module.user.model to javafx.fxml, com.google.gson;
    exports org.ess;
    exports org.ess.app.model;
    exports org.ess.app.controller;
    exports org.ess.module.user.Event;
    opens org.ess.module.user.repository to com.google.gson, javafx.fxml;
    opens org.ess.module.user.service to com.google.gson, javafx.fxml;
    opens org.ess.app.model to com.google.gson;
    exports org.ess.app.common;
    opens org.ess.module.asset.controller to javafx.fxml;
    opens org.ess.module.asset.model to com.google.gson, javafx.fxml;
    opens org.ess.module.asset.repository to com.google.gson, javafx.fxml;
    opens org.ess.module.asset.service to com.google.gson, javafx.fxml;
    opens org.ess.module.event.controller to com.google.gson, javafx.fxml;
    opens org.ess.module.event.model to com.google.gson, javafx.fxml;
    opens org.ess.module.bookings.controller to com.google.gson, javafx.fxml;
    opens org.ess.module.bookings.model to com.google.gson, javafx.fxml;
    opens org.ess.module.invoice.controller to com.google.gson, javafx.fxml;
    opens org.ess.module.invoice.request to com.google.gson;
    opens org.ess.app to com.google.gson, javafx.fxml;
    exports org.ess.app.auth;
    opens org.ess.app.auth to com.google.gson, javafx.fxml, lombok;
}