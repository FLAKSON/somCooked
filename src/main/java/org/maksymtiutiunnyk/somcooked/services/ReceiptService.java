package org.maksymtiutiunnyk.somcooked.services;

import org.maksymtiutiunnyk.somcooked.dtos.ReceiptCreationDto;
import org.maksymtiutiunnyk.somcooked.entities.ReceiptEntity;
import org.maksymtiutiunnyk.somcooked.repositories.ReceiptRepository;
import org.maksymtiutiunnyk.somcooked.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReceiptService {

    private final ReceiptRepository receiptRepository;
    private final UserInfoService userInfoService;
    private final UserRepository userRepository;

    public ReceiptService(ReceiptRepository receiptRepository, UserInfoService userInfoService, UserRepository userRepository) {
        this.receiptRepository = receiptRepository;
        this.userInfoService = userInfoService;
        this.userRepository = userRepository;
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

    public void deleteReceiptById(Long id) {
        receiptRepository.deleteById(id);
    }

    public void editReceipt(Long id, ReceiptCreationDto receipt) {
        ReceiptEntity receiptForEdit = getReceiptById(id);
        receiptForEdit.setDescription(receipt.description());
        receiptForEdit.setIngredients(receipt.ingredients());
        receiptForEdit.setSteps(receipt.steps());
        receiptForEdit.setPublicFlag(receipt.publicFlag());
        receiptForEdit.setTitle(receipt.title());
        receiptRepository.save(receiptForEdit);
    }

    public ArrayList<ReceiptEntity> getAllReceipts() {
        ArrayList<ReceiptEntity> receipts = new ArrayList<>();
        for (ReceiptEntity receipt : receiptRepository.findAll()) {
            if (receipt.isPublicFlag()) {
                receipts.add(receipt);
            }
        }
        return receipts;
    }
}
