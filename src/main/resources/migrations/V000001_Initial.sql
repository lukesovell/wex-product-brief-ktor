-- I still haven't integrated flyway yet, but JOOQ uses this for code generation.
CREATE TABLE IF NOT EXISTS payment
(
    id               UUID           NOT NULL,
    description      VARCHAR(50)    NOT NULL,
    transaction_date BIGINT         NOT NULL,
    purchase_amount  DECIMAL(15, 2) NOT NULL,
    currency         VARCHAR(50)    NOT NULL,
    CONSTRAINT pk_payment PRIMARY KEY (id)
);