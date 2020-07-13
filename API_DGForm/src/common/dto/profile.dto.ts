export class UserProfileResponse {
  username: string;
  email: string;
  image: string;
  constructor({ username, email, image }) {
    this.email = email;
    this.username = username;
    this.image = image;
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
