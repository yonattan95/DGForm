export interface NewFormI {
  description: string;
  name: string;
  startDate: string;
  endDate: string;
  state: number;
  allQuizAssigned: number;

  user: number;
  category: number;
}


export interface AssignedIntervierwerToFormI{
  interviewer:number;
  form:number;
  startDate:string;
  endDate:string;
  quizzesNumberAssigned:number;
}