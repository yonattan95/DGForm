export interface InterviewerI {
  id: number | null;
  userId: number;
  name: string;
  surname1: string;
  surname2: string;
  username: string;
  email: string;
  password: string;
  state: number;
  image:string;
}


export interface NewInterviewerI {
  userId: number;
  name: string;
  surname1: string;
  surname2: string;
  username: string;
  email: string;
  password: string;
}
export interface UpdateInterviewerI {
  name: string;
  surname1: string;
  surname2: string;
  username: string;
  email: string;
}
