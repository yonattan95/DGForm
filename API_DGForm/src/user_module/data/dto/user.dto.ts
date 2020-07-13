export class UserRequest {
  username: string;
  state = 1;
  password: string;
  email: string;
  constructor({ username, email, password }) {
    this.email = email;
    this.username = username;
    this.password = password;
  }
}
export class UserResponse {
  username: string;
  state: number;
  email: string;
  constructor({ username, email, state }) {
    this.email = email;
    this.username = username;
    this.state = state;
  }
}
