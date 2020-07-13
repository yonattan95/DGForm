export interface InterviewerI {
  id: number | null;
  userId: number;
  username: string;
  email: string;
  password: string;
  state: number;
}


export interface NewInterviewerI {
  userId: number;
  username: string;
  email: string;
  password: string;
}
export interface UpdateInterviewerI {
  username: string;
  email: string;
}
