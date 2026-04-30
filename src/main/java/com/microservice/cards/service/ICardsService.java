package com.microservice.cards.service;

import com.microservice.cards.dto.CardsDto;

public interface ICardsService {

    /**
     * Creates a card for the customer associated with the provided mobile number.
     *
     * @param mobileNumber the mobile number of the customer for whom the card is to be created.
     *                      This must be a non-empty string containing exactly 10 digits.
     */
    void createCard(String mobileNumber);


    /**
     * Fetches the card details associated with the provided mobile number.
     *
     * @param mobileNumber the mobile number of the customer for whom the card details are to be retrieved.
     *                     This must be a non-empty string containing exactly 10 digits.
     * @return a {@link CardsDto} object containing the card details for the specified mobile number.
     */
    CardsDto fetchCard(String mobileNumber);


    /**
     * Updates the details of an existing card based on the provided card information.
     *
     * @param cardsDto an object of {@link CardsDto} containing the updated card details.
     *                 This must include a valid mobile number, card number, card type,
     *                 total limit, amount used, and available amount.
     * @return {@code true} if the update was successful*/
    boolean updateCard(CardsDto cardsDto);


    /**
     * Deletes the card associated with the provided mobile number.
     *
     * @param mobileNumber the mobile number of the customer whose card is to be deleted.
     *                     This must be a non-empty string containing exactly 10 digits.
     * @return {@code true} if the deletion was successful, {@code false} otherwise.
     */
    boolean deleteCard(String mobileNumber);

}
