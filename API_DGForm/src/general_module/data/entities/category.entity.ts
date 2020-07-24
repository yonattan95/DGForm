import { Column, Entity, PrimaryGeneratedColumn } from 'typeorm';

@Entity()
export default class Category {
  @PrimaryGeneratedColumn()
  id: number;

  @Column()
  name: string;
}
