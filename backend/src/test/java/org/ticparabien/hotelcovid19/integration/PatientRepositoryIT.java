package org.ticparabien.hotelcovid19.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.ticparabien.hotelcovid19.domain.Patient;
import org.ticparabien.hotelcovid19.domain.repositories.PatientRepository;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@DataJpaTest
class PatientRepositoryIT {

    private static final String NAME = "name";

    private static final String PERSONAL_ID = "personalId";

    private static final String PHONE = "phone";

    @Autowired
    private PatientRepository repository;

    @Test
    void given_AnExistingPersonalId_When_RetrievingPatientByPersonalId_Then_GetEntity() {
        Patient patient = Patient.builder()
                .name(NAME)
                .personalId(PERSONAL_ID)
                .phone(PHONE)
                .build();
        repository.saveAndFlush(patient);

        Optional<Patient> optional = repository.findByPersonalId(PERSONAL_ID);

        assertThat(optional.isPresent(), is(true));
        assertThat(optional.get().getName(), is(NAME));
        assertThat(optional.get().getPersonalId(), is(PERSONAL_ID));
        assertThat(optional.get().getPhone(), is(PHONE));
    }

    @Test
    void given_ANonExistingPersonalId_When_RetrievingPatientByPersonalId_Then_GetEmpty() {
        Optional<Patient> optional = repository.findByPersonalId(PERSONAL_ID);

        assertThat(optional.isPresent(), is(false));
    }
}
