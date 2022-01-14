export class Match {
  constructor(public id: string,
              public team1: string,
              public team2: string,
              public year: number,
              public date: Date,
              public venue: string,
              public tossWinner: string,
              public tossDecision: string,
              public winner: string,
              public playerOfMatch: string,
              public result: string,
              public umpire1: string,
              public umpire2: string) {
  }
}
