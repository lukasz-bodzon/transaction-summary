package org.lbodzon.sample.txn.summary.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class Client {

        private String name;

        private String surname;

        public Client(Client other) {
                this.name = other.name;
                this.surname = other.surname;
        }
}
