export class FormRequest {
  name: string;
  description: string;
  status: number;

  constructor(name: string, description: string, status: number) {
    this.name = name;
    this.description = description;
    this.status = status;
  }
}
export class FormResponse {
//   id: number;
  name: string;
  description: string;
  status: number;

  constructor(
    id: number,
    name: string,
    description: string,
    status: number,
  ) {
    // this.id = id;
    this.name = name;
    this.description = description;
    this.status = status;
  }
}
