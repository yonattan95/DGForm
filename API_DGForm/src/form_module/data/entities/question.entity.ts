import { Column, Entity, PrimaryColumn } from 'typeorm';

@Entity()
export default class Question {
  @PrimaryColumn()
  id: number;

  @Column()
  name: string;

  @Column()
  description: string;
  @Column({ name: 'form_id' })
  formId: number;
}
