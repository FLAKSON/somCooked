package org.maksymtiutiunnyk.somcooked.services;

import org.maksymtiutiunnyk.somcooked.entities.UserEntity;
import org.maksymtiutiunnyk.somcooked.repositories.ReceiptRepository;
import org.maksymtiutiunnyk.somcooked.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserInfoService {
    private final UserRepository userRepository;
    private final ReceiptRepository receiptRepository;

    @Autowired
    public UserInfoService(UserRepository userRepository, ReceiptRepository receiptRepository) {
        this.userRepository = userRepository;
        this.receiptRepository = receiptRepository;
    }

    public String getUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    public UserEntity getUser() {
        return userRepository.findByUsername(getUsername()).get();
    }
}
