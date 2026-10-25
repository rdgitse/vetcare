CREATE TABLE app_users (
    id            BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    username      VARCHAR(50)  NOT NULL UNIQUE,
    password_hash VARCHAR(60)  NOT NULL,
    full_name     VARCHAR(120) NOT NULL,
    role          VARCHAR(20)  NOT NULL CHECK (role IN ('ADMIN', 'VET', 'RECEPTIONIST')),
    active        BOOLEAN      NOT NULL DEFAULT TRUE,
    created_at    TIMESTAMPTZ  NOT NULL DEFAULT now()
);

CREATE TABLE owners (
    id          BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    document_id VARCHAR(20)  NOT NULL UNIQUE,
    full_name   VARCHAR(120) NOT NULL,
    phone       VARCHAR(20)  NOT NULL,
    email       VARCHAR(120) UNIQUE

); 

CREATE TABLE pets (
    id         BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    owner_id   BIGINT NOT NULL REFERENCES owners(id),
    name       VARCHAR(60) NOT NULL,
    species    VARCHAR(20) NOT NULL CHECK (species IN ('DOG', 'CAT', 'BIRD', 'OTHER')),
    breed      VARCHAR(60),
    birth_date DATE
);

CREATE TABLE vets (
    id          BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    license_number VARCHAR(20)  NOT NULL UNIQUE,
    full_name   VARCHAR(120) NOT NULL,
    specialty       VARCHAR(20)  NOT NULL
);

CREATE TABLE appointments (
    id           BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    pet_id       BIGINT NOT NULL REFERENCES pets(id),
    vets_id      BIGINT NOT NULL REFERENCES vets(id),
    scheduled_at TIMESTAMPTZ  NOT NULL,
    reason       VARCHAR(200) NOT NULL,
    status       VARCHAR(20)  NOT NULL DEFAULT 'SCHEDULED'
                 CHECK (status IN ('SCHEDULED', 'DONE', 'CANCELLED')),
    fee          NUMERIC(10,2) NOT NULL DEFAULT 0
);

CREATE TABLE services (
    id     BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name   VARCHAR(100)  NOT NULL UNIQUE,
    price  NUMERIC(10,2) NOT NULL CHECK (price >= 0)
);

CREATE TABLE consultations (
    id             BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    appointment_id BIGINT NOT NULL UNIQUE REFERENCES appointments(id),
    reason         VARCHAR(255) NOT NULL,
    diagnosis      TEXT         NOT NULL,
    treatment      TEXT         NOT NULL
);

CREATE TABLE invoices (
    id     BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    consultations_id BIGINT NOT NULL UNIQUE REFERENCES consultations(id),
    total   numeric(10,2)  NOT NULL CHECK (total >= 0),
    issued_at  timestamp NOT NULL default NOW()
);

CREATE TABLE invoice_items (
    id         BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    invoice_id BIGINT NOT NULL REFERENCES invoices(id),
    service_id BIGINT NOT NULL REFERENCES services(id),
    quantity   INT    NOT NULL CHECK (quantity > 0),
    subtotal   NUMERIC(10,2) NOT NULL CHECK (subtotal >= 0)
);