
export interface StudentDtoRequest {
    firstName: string;
    lastName: string;
    emailPro: string;
    emailPerso: string;
    phoneNumber: string;
    address: string;
    registrationNu: string;
    archive: boolean;
  }
  

  export interface StudentDtoResponse {
    id: number;
    firstName: string;
    lastName: string;
    emailPro: string;
    emailPerso: string;
    phoneNumber: string;
    address: string;
    registrationNu: string;
    archive: boolean;
  }