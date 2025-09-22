package org.maksymtiutiunnyk.somcooked.services;

import org.maksymtiutiunnyk.somcooked.dtos.ReceiptCreationDto;
import org.maksymtiutiunnyk.somcooked.entities.ReceiptEntity;
import org.maksymtiutiunnyk.somcooked.repositories.ReceiptRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ReceiptService {

    private final ReceiptRepository receiptRepository;
    private final UserInfoService userInfoService;

    public ReceiptService(ReceiptRepository receiptRepository, UserInfoService userInfoService) {
        this.receiptRepository = receiptRepository;
        this.userInfoService = userInfoService;
    }

    public void addReceipt(ReceiptCreationDto receipt) {
        try {
            ReceiptEntity receiptEntity = new ReceiptEntity();
            receiptEntity.setCreationDate(LocalDate.now());
            receiptEntity.setDescription(receipt.description());
            receiptEntity.setIngredients(receipt.ingredients());
            receiptEntity.setSteps(receipt.steps());
            receiptEntity.setPublicFlag(receipt.publicFlag());
            receiptEntity.setUser(userInfoService.getUser());
            receiptEntity.setTitle(receipt.title());
            receiptRepository.save(receiptEntity);
        }
        catch (Exception e) {
            System.err.println("Error adding recipe: " + e.getMessage());
        }

    }
}
