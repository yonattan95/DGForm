import {
  Column,
  Entity,
  PrimaryGeneratedColumn,
  Table,
} from 'typeorm';

@Entity()
export class User {
  constructor() {}
  @PrimaryGeneratedColumn()
  id: number;

  @Column()
  username: string;

  @Column()
  password: string;

  @Column()
  email: string;

  @Column()
  state: number;
}
