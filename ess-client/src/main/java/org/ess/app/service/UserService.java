package org.ess.app.service;

import org.ess.app.model.User;
import org.ess.app.common.HttpClient;
import org.ess.app.repository.AuthRepository;
import retrofit2.Callback;

public class UserService {

    public void auth(User user, Callback<User> callback) {
        HttpClient.use(AuthRepository.class).post(user).enqueue(callback);
    }
}
