export class UserRequest {
  username: string;
  state = 1;
  password: string;
  email: string;

  rol:string;
  constructor({ username, email, password,rol }) {
    this.email = email;
    this.username = username;
    this.password = password;
    this.rol = rol;
  }
}
export class UserResponse {
  username: string;
  state: number;
  email: string;

  rol:string;
  constructor({ username, email, state ,rol}) {
    this.email = email;
    this.username = username;
    this.state = state;
    this.rol = rol;
  }
}
