# Gerenciador Hospitalar de Prontuarios de Pacientes
**(30/09/2021)**

Projeto em Java com o objetivo de registrar os dados de prontuarios de pacientes, além de gerenciar-los.
O programa é amigável a um fluxo de atendimento em tempo real.

Por meio da *serialização*, os dados ficam salvos em arquivo *.byte*, e o local deste arquivo pode ser mudado ao editar a string _**camHosp**_, presente em *Main.java*

É possível cadastrar medicos e enfermeiros o sistema, além de registrar pacientes e prontuarios.

Um prontuario tem as seguintes informações:
* Paciente;
* Medico resposavel;
* Enfermeiro responsavel;
* Risco do paciente (Azul, Verde, Amarelo ou Vermelho);
* Data e hora no qual o risco do paciente foi avaliado pela enfermagem;
* Data e hora do atendimento médico;
* Data e hora da alta médica;
* Internação.

Feito para avaliação da cadeira de Linguagem de Programação II, que foi ministrada pelo docente Fábio Júnior Francisco, no curso de Computação da UEPB.
***
![image](https://user-images.githubusercontent.com/58227057/140577756-d70f683d-f921-4617-8872-1f7713bcf25b.png)


![Fluxograma extremamente competente](https://user-images.githubusercontent.com/58227057/140577056-7dadd2c8-6de1-494d-b2af-e892c007b37f.png)

Fluxograma ilustrativo.
***


![image](https://user-images.githubusercontent.com/58227057/140580106-dac3dbc2-662e-4d69-8917-a60a96ecb527.png)

Interface da aplicação.
