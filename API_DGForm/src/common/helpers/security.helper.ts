const crypto = require('crypto');
const secret = 'ElMejorGrupoDeIntegradorII';
export const hashString = (valor: string) => {
  return crypto
    .createHmac('sha256', secret)
    .update(valor)
    .digest('hex');
};
