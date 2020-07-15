export class NewInterviewerRequest {
  id: null;
  username: string;
  password: string;
  email: string;
  state = 1;
  userId: number;
  image: string;

  name: string;
  surname1: string;
  surname2: string;
  constructor({
    username,
    email,
    password,
    userId,
    image,
    name,
    surname1,
    surname2,
  }) {
    this.email = email;
    this.username = username;
    this.password = password;
    this.userId = userId;
    this.image = image;
    this.name = name;
    this.surname1 = surname1;
    this.surname2 = surname2;
  }
}

export class InterviewerProfile {
  name: string;
  surname1: string;
  surname2: string;
  username: string;
  email: string;
  state: number;

  image: string;

  constructor({
    username,
    email,
    state,
    image,
    name,
    surname1,
    surname2,
  }) {
    this.email = email;
    this.username = username;
    this.state = state;
    this.image = image;
    this.name = name;
    this.surname1 = surname1;
    this.surname2 = surname2;
  }
}
