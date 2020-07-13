export class NewInterviewerRequest {
  id: null;
  username: string;
  password: string;
  email: string;
  state = 1;
  userId: number;
  constructor({ username, email, password, userId }) {
    this.email = email;
    this.username = username;
    this.password = password;
    this.userId = userId;
  }
}

export class InterviewerProfile {
  username: string;
  email: string;
  state: number;

  constructor({ username, email, state }) {
    this.email = email;
    this.username = username;
    this.state = state;
  }
}
