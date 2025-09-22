package org.maksymtiutiunnyk.somcooked.dtos;

public record ReceiptCreationDto(String title, String description, String ingredients, String steps, boolean publicFlag) {
}
