const readline = require('readline-sync');

// Função para definir a dificuldade do jogo
function dificuldade() {
    console.log("<==== Escolha a Dificuldade ====>");
    console.log("\n1- Fácil (Iniciante)");
    console.log("2- Normal (Amadores)");
    console.log("3- Difícil (Profissionais)");
    console.log("<=============================>");

    let escolha = readline.question("Qual a Dificuldade? ");
    switch (escolha) {
        case "1":
            console.log("Você escolheu a dificuldade Fácil.");
            break;
        case "2":
            inimigos.forEach(inimigo => {
                inimigo.ataque += 50;
                inimigo.vida += 50;
                inimigo.defesa += 10;
            });
            console.log("Você escolheu a dificuldade Normal.");
            break;
        case "3":
            inimigos.forEach(inimigo => {
                inimigo.ataque += 50;
                inimigo.vida += 80;
                inimigo.defesa += 20;
            });
            console.log("Você escolheu a dificuldade Difícil.");
            break;
        default:
            console.log("Opção inválida. Dificuldade padrão (Fácil) selecionada.");
            break;
    }
}

// Função para criar o personagem do jogador
function criarPersonagem() {
    console.log("Bem-vindo ao RPG de Aventura!");
    const nome = readline.question("Qual o seu nome, aventureiro? ");

    console.log("Escolha sua classe:");
    console.log("1. Guerreiro (+Ataque)");
    console.log("2. Mago (+Magia)");
    console.log("3. Arqueiro (+Esquiva)");
    const classe = parseInt(readline.question("Opção: "));

    let personagem = {
        nome: nome,
        classe: classe,
        vida: 500,
        ataque: 20,
        defesa: 10,
        magia: 0,
        esquiva: 5,
        dinheiro: 100,
        energia: 100,
        nivel: 1,
        experiencia: 0,
    };

    switch (classe) {
        case 1:
            personagem.ataque += 10;
            break;
        case 2:
            personagem.magia += 10;
            break;
        case 3:
            personagem.esquiva += 10;
            break;
        default:
            console.log("Classe inválida. Guerreiro selecionado por padrão.");
            personagem.classe = 1;
            personagem.ataque += 10;
            break;
    }
    return personagem;
}

// Lista de inimigos
const inimigos = [
    { nome: "Goblin", nivel: 1, vida: 50, ataque: 8, defesa: 3, experiencia: 20, vidafixa: 50, },
    { nome: "Orc", nivel: 1, vida: 80, ataque: 12, defesa: 5, experiencia: 30, vidafixa: 80, },
    { nome: "Dragão", nivel: 1, vida: 200, ataque: 25, defesa: 10, experiencia: 70, vidafixa: 200, },
    { nome: "Esqueleto", nivel: 1, vida: 60, ataque: 10, defesa: 4, experiencia: 25, vidafixa: 60, },
    { nome: "Mago Negro", nivel: 1, vida: 100, ataque: 18, defesa: 7, experiencia: 50, vidafixa: 100, },
    { nome: "Troll", nivel: 1, vida: 150, ataque: 20, defesa: 8, experiencia: 60, vidafixa: 150, },
];

// Boss final
const boss = { nome: "Senhor das Trevas", vida: 500, ataque: 30, defesa: 15, vidafixa: 500, };

// Missões
const missoes = [
    "Derrote 3 inimigos",
    "Alcance o nível 5",
    "Acumule 200 moedas",
    "Derrote o Boss Final"
];
let missoesCompletas = [false, false, false, false];

// Variáveis globais do jogo
let personagem = criarPersonagem();
let inimigosDerrotados = 0;
let bossDerrotado = false;

// Loop principal do jogo
let jogoAtivo = true;
dificuldade();
while (jogoAtivo) {
    console.log("\nO que deseja fazer?");
    console.log("1. Explorar");
    console.log("2. Descansar");
    console.log("3. Loja");
    console.log("4. Status");
    console.log("5. Missões");
    console.log("6. Enfrentar o Boss");
    console.log("7. Sair");
    const escolha = parseInt(readline.question("Escolha: "));

    switch (escolha) {
        case 1:
            console.log("\nVocê decide explorar...");
            let inimigo = { ...inimigos[Math.floor(Math.random() * inimigos.length)] };
            console.log(`Você encontrou um ${inimigo.nome}!`);

            while (inimigo.vida > 0 && personagem.vida > 0) {
                console.log("\n1. Atacar");
                console.log("2. Defender");
                console.log("3. Fugir");
                const acao = parseInt(readline.question("Escolha: "));

                if (acao === 1) { // vida do inimigo não fica menor que zero, aparece quantro de vida resta para ambos
                    let dano = Math.max(personagem.ataque - inimigo.defesa, 5);
                    inimigo.vida = Math.max(inimigo.vida - dano, 0);
                    console.log(`Você causou ${dano} de dano no ${inimigo.nome}!`);
                    console.log(`Vida restante do ${inimigo.nome}: ${inimigo.vida}/${inimigo.vidafixa}`);
                } else if (acao === 2) {
                    console.log("Você se defendeu e recebeu menos dano!");
                    personagem.defesa *= 1.5;
                    console.log(`Vida do ${inimigo.nome}: ${inimigo.vida}/${inimigo.vidafixa}`);
                } else if (acao === 3) { // Adicionando a fuga do personagem com chance de fracasso e penalidade.
                    let chanceEscape = Math.random() * 100;
                    if (chanceEscape > 40) {
                        console.log("Você escapou!");
                        break;
                    } else {
                        console.log(`Você tentou escapar e tropeçou numa pedra e o ${inimigo.nome} te desferiu um ataque!`);
                    }
                }

                if (inimigo.vida > 0) {
                    let danoRecebido = Math.max(inimigo.ataque - personagem.defesa, 5);
                    if (Math.random() * 100 < personagem.esquiva) {
                        console.log("Você esquivou do ataque!");
                        danoRecebido = 0;
                    }
                    personagem.vida -= danoRecebido;
                    console.log(`${inimigo.nome} causou ${danoRecebido} de dano em você.\nVida restante do jogador: ${personagem.vida}`);// aparecer vida do personagem apos o ataque
                }

                if (inimigo.vida <= 0) {
                    console.log(`Você derrotou o ${inimigo.nome}!`);
                    personagem.experiencia += inimigo.experiencia;
                    personagem.dinheiro += 20;
                    inimigosDerrotados++;
                    if (personagem.experiencia >= 50) {
                        personagem.nivel++;
                        personagem.vida += 30;
                        personagem.ataque += 8;
                        personagem.defesa += 5;
                        personagem.energia = 100;
                        personagem.experiencia -= 50;
                        console.log(`Parabéns! Você subiu para o nível ${personagem.nivel}!`);
                    }
                }

                if (personagem.vida <= 0) {
                    console.log("Você morreu! Fim de jogo.");
                    personagem.experiencia = inimigo.experiencia;
                    if (personagem.experiencia >= 50) {
                        personagem.nivel++;
                        personagem.vida += 30;
                        personagem.ataque += 8;
                        personagem.defesa += 5;
                        personagem.energia = 100;
                        personagem.experiencia -= 50;
                        console.log(`Parabéns! Você subiu para o nível ${personagem.nivel}!`);
                    }
                    personagem.defesa = Math.max(personagem.defesa / 1.5, 10);
                    break;
                }
                personagem.defesa = Math.max(personagem.defesa / 1.5, 10);
            }
            break;

        case 2:
            console.log("\nVocê decide descansar...");
            personagem.vida = Math.min(500, personagem.vida + 50);
            personagem.energia = Math.min(100, personagem.energia + 30);
            console.log("Sua vida e energia foram restauradas um pouco.");
            break;

        case 3:
            console.log("\nBem-vindo à loja!");
            console.log("1. Poção de Vida (50 moedas) - Restaura 50 de vida");
            console.log("2. Poção de Energia (30 moedas) - Restaura 30 de energia");
            console.log("3. Sair");
            const escolhaLoja = parseInt(readline.question("Escolha: "));

            if (escolhaLoja === 1 && personagem.dinheiro >= 50) {
                personagem.dinheiro -= 50;
                personagem.vida = Math.min(500, personagem.vida + 50);
                console.log("Você comprou uma Poção de Vida!");
            } else if (escolhaLoja === 2 && personagem.dinheiro >= 30) {
                personagem.dinheiro -= 30;
                personagem.energia = Math.min(100, personagem.energia + 30);
                console.log("Você comprou uma Poção de Energia!");
            } else {
                console.log("Dinheiro insuficiente ou opção inválida.");
            }
            break;

        case 4:
            console.log("\n===== STATUS DO JOGADOR =====");
            console.log(`Nome: ${personagem.nome}`);
            console.log(`Classe: ${personagem.classe === 1 ? "Guerreiro" : personagem.classe === 2 ? "Mago" : "Arqueiro"}`);
            console.log(`Nível: ${personagem.nivel}`);
            console.log(`Experiência: ${personagem.experiencia}/50`);
            console.log(`Vida: ${personagem.vida}`);
            console.log(`Energia: ${personagem.energia}`);
            console.log(`Ataque: ${personagem.ataque}`);
            console.log(`Defesa: ${personagem.defesa}`);
            console.log(`Magia: ${personagem.magia}`);
            console.log(`Esquiva: ${personagem.esquiva}`);
            console.log(`Dinheiro: ${personagem.dinheiro}`);
            console.log("=============================");
            break;

        case 5:
            console.log("\n===== MISSÕES =====");
            for (let i = 0; i < missoes.length; i++) {
                console.log(`${i + 1}. ${missoes[i]} - ${missoesCompletas[i] ? "Completa" : "Incompleta"}`);
            }
            break;

        case 6:
            if (inimigosDerrotados >= 3) {
                console.log("Você decide enfrentar o Boss!");
                while (boss.vida > 0 && personagem.vida > 0) {
                    console.log("\n1. Atacar");
                    console.log("2. Defender");
                    console.log("3. Fugir")
                    const acaoBoss = parseInt(readline.question("Escolha: "));

                    if (acaoBoss === 1) {
                        let dano = Math.max(personagem.ataque - boss.defesa, 10);
                        boss.vida -= dano;
                        console.log(`Você causou ${dano} de dano no ${boss.nome}!`);
                        console.log(`Vida restante do Boss: ${boss.vida}/${boss.vidafixa}`);// aparecer vida do boss
                    } else if (acaoBoss === 2) {
                        console.log("Você se defendeu e recebeu menos dano!");
                        personagem.defesa *= 1.5;
                    } else if (acao === 3) { // escapar boss com punição caso não consiga
                        let chanceEscape = Math.random() * 100;
                        if (chanceEscape > 70) {
                            console.log("Você escapou do Boss!");
                            break;
                        } else {
                            console.log(console.log(`Você tentou escapar e tropeçou numa pedra e o ${boss.nome} te desferiu um ataque!`));
                            boss.vida = boss.vidafixa;
                        }
                    }

                    if (boss.vida > 0) {
                        let danoRecebido = Math.max(boss.ataque - personagem.defesa, 15);
                        if (Math.random() * 100 < personagem.esquiva) {
                            console.log("Você esquivou do ataque!");
                            danoRecebido = 0;
                        }
                        personagem.vida -= danoRecebido;
                        console.log(`${boss.nome} causou ${danoRecebido} de dano em você.\nVida restante do jogador: ${personagem.vida}`);
                    }

                    if (boss.vida <= 0) {
                        console.log("Parabéns! Você derrotou o Senhor das Trevas!");
                        bossDerrotado = true;
                        missoesCompletas[3] = true;
                        break;
                    }

                    if (personagem.vida <= 0) {
                        console.log("Você foi derrotado pelo Senhor das Trevas...");
                        jogoAtivo = false;
                    }
                    personagem.defesa = Math.max(personagem.defesa / 1.5, 10);
                }
            } else {
                console.log("Você ainda não completou os requisitos para enfrentar o Boss.");
            }
            break;

        case 7:
            console.log("Obrigado por jogar! Até a próxima.");
            jogoAtivo = false;
            break;

        default:
            console.log("Opção inválida. Tente novamente.");
    }
}