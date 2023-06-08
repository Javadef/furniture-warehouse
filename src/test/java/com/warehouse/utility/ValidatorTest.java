package com.warehouse.utility;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidatorTest {

    @Test
    void validateName_ValidName_ReturnsTrue() {
        assertTrue(Validator.validateName("Product Name"));
    }

    @Test
    void validateName_EmptyName_ThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> Validator.validateName(""));
    }

    @Test
    void validateCategory_ValidCategory_ReturnsTrue() {
        assertTrue(Validator.validateCategory("Electronics"));
    }

    @Test
    void validateCategory_EmptyCategory_ThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> Validator.validateCategory(""));
    }

    @Test
    void validatePrice_ValidPrice_ReturnsTrue() {
        assertTrue(Validator.validatePrice(10.99));
    }

    @Test
    void validatePrice_NegativePrice_ThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> Validator.validatePrice(-5.99));
    }

    @Test
    void validateQuantity_ValidQuantity_ReturnsTrue() {
        assertTrue(Validator.validateQuantity(100));
    }

    @Test
    void validateQuantity_NegativeQuantity_ThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> Validator.validateQuantity(-50));
    }

    @Test
    void validateManufacturer_ValidManufacturer_ReturnsTrue() {
        assertTrue(Validator.validateManufacturer("ABC Manufacturing"));
    }

    @Test
    void validateManufacturer_EmptyManufacturer_ThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> Validator.validateManufacturer(""));
    }

    @Test
    void validateWeight_ValidWeight_ReturnsTrue() {
        assertTrue(Validator.validateWeight(2.5));
    }

    @Test
    void validateWeight_NegativeWeight_ThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> Validator.validateWeight(-1.5));
    }

    @Test
    void validateDimensions_ValidDimensions_ReturnsTrue() {
        assertTrue(Validator.validateDimensions("10x20x5"));
    }

    @Test
    void validateDimensions_EmptyDimensions_ThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> Validator.validateDimensions(""));
    }

    @Test
    void validateCommand_ValidCommand_ReturnsTrue() {
        assertTrue(Validator.validateCommand("add"));
    }

    @Test
    void validateCommand_EmptyCommand_ThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> Validator.validateCommand(""));
    }

    @Test
    void validateCommand_InvalidCommand_ThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> Validator.validateCommand("invalid"));
    }

    @Test
    void validateProductId_ValidProductId_ReturnsTrue() {
        assertTrue(Validator.validateProductId(1234));
    }

    @Test
    void validateProductId_ZeroProductId_ThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> Validator.validateProductId(0));
    }

    @Test
    void validateProductId_NegativeProductId_ThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> Validator.validateProductId(-123));
    }

    @Test
    void validateSearchParameter_ValidParameter_ReturnsTrue() {
        assertTrue(Validator.validateSearchParameter("name"));
    }

    @Test
    void validateSearchParameter_InvalidParameter_ReturnsFalse() {
        assertFalse(Validator.validateSearchParameter("invalid"));
    }

    @Test
    void validatePriceParameter_ValidRange_ReturnsTrue() {
        assertTrue(Validator.validatePriceParameter("10-20"));
    }

    @Test
    void validatePriceParameter_EmptyInput_ReturnsFalse() {
        assertFalse(Validator.validatePriceParameter(""));
    }

    @Test
    void validatePriceParameter_InvalidNumericValues_ReturnsFalse() {
        assertFalse(Validator.validatePriceParameter("10-abc"));
    }

    @Test
    void validatePriceParameter_NegativeMinimumValue_ReturnsFalse() {
        assertFalse(Validator.validatePriceParameter("-10-20"));
    }

    @Test
    void validatePriceParameter_MaximumLessThanMinimum_ReturnsFalse() {
        assertFalse(Validator.validatePriceParameter("20-10"));
    }
}
