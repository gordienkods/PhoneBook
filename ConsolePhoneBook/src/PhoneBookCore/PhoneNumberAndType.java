package PhoneBookCore;

import java.io.Serializable;

public class PhoneNumberAndType implements Serializable {

        String phoneNumber = "undefined";
        String phoneType = "undefined";

        public PhoneNumberAndType(String phoneNumber, String phoneType) {
            this.phoneNumber = phoneNumber;
            this.phoneType = phoneType;
        }

        public PhoneNumberAndType(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public String getPhoneType() {
            return phoneType;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public void setPhoneType(String phoneType) {
            this.phoneType = phoneType;
        }

        @Override
        public int hashCode() {
            int baseValue = 111;
            int result = 1;
            result = baseValue * result + this.phoneNumber.hashCode();
            result = baseValue * result + this.getPhoneType().hashCode();
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (this.getClass() != obj.getClass()) {
                return false;
            }
            PhoneNumberAndType other = (PhoneNumberAndType) obj;
            if (!this.phoneNumber.equals(other.phoneNumber)) {
                return false;
            }
            if (!this.phoneType.equals(other.phoneType)) {
                return false;
            }
            return true;
        }
}


