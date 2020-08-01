export class UserProfileResponse {
  username: string;
  email: string;
  image: string;
  rol: string;
  constructor({ username, email, image, rol }) {
    this.email = email;
    this.username = username;
    this.image = image;
    this.rol = rol;
  }
}
export class InterviewerProfileResponse {
  username: string;
  email: string;
  image: string;
  constructor({ username, email, image }) {
    this.email = email;
    this.username = username;
    this.image = image;
  }
}
