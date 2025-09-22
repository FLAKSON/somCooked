package org.maksymtiutiunnyk.somcooked.services;

import org.maksymtiutiunnyk.somcooked.dtos.ReceiptCreationDto;
import org.maksymtiutiunnyk.somcooked.entities.ReceiptEntity;
import org.maksymtiutiunnyk.somcooked.repositories.ReceiptRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    public ArrayList<ReceiptEntity> getAllReceiptsForUser() {
        ArrayList<ReceiptEntity> receipts = new ArrayList<>();
        for (ReceiptEntity receipt : receiptRepository.findAll()) {
            if (receipt.getUser().getId().equals(userInfoService.getUser().getId())) {
                receipts.add(receipt);
            }
        }
        return receipts;
    }

    public ReceiptEntity getReceiptById(Long id) {
        return receiptRepository.findById(id).orElse(null);
    }
}
