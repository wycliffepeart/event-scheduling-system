package org.ess.module.user.route;

import org.ess.app.common.Navigate;

public class UserRoute {

    public static void deleteConfirmationLayout(){
        Navigate.toWindow("Delete User","user_delete_confirmation_controller_layout.fxml", 500, 400);
    }

    public static void userFormLayout(){
        Navigate.toWindow("User Form","user_form_controller_layout.fxml");
    }
}
