export interface RegistrationDtoRequest {
    id?: number;
    studentId: number;
    classeId: number;
    description: string;
    archive: boolean;
    date?: string;
    classeName:String;
    classeDescription:String;
    studentFirstName:String;
    studentLastName:String;
    studentEmilPro:String;
    studentEmailPerso:String;
    studentRegistrationNu:String;
    studentAddress:String;
    studentPhoneNumber:String;
  }
  export interface RegistrationDtoResponse {
    id?: number;
    studentId: number;
    classeId: number;
    description: string;
    archive: boolean;
    date?: string;
    classeName:String;
    classeDescription:String;
    studentFirstName:String;
    studentLastName:String;
    studentEmilPro:String;
    studentEmailPerso:String;
    studentRegistrationNu:String;
    studentAddress:String;
    studentPhoneNumber:String;
  }