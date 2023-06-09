package eafcanghel.pcshop.payment;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public enum PaymentMethod {

    VISA,
    MC,
    BANK_TRANSFER

}
