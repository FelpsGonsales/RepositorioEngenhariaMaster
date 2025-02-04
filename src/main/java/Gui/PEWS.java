import java.util.Scanner;

class BPEWS {
    private String nomeAvaliador;
    private String matricula;
    private String funcao;

    private String nomeCrianca;
    private String leito;
    private String dih;
    private String diagnostico;
    private int idade;
    private String dataAvaliacao;

    private int freqCardiaca;
    private int freqRespiratoria;

    private int avaliacaoNeurologica;
    private int avaliacaoCardiovascular;
    private int avaliacaoRespiratoria;

    private int nebulizacaoResgate;
    private int emesePosOperatorio;

    public void coletarDados() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Identificação do Avaliador ===");
        System.out.print("Nome: ");
        nomeAvaliador = scanner.nextLine();
        System.out.print("Matrícula: ");
        matricula = scanner.nextLine();
        System.out.print("Função: ");
        funcao = scanner.nextLine();

        System.out.println("\n=== Identificação da Criança ===");
        System.out.print("Nome: ");
        nomeCrianca = scanner.nextLine();
        System.out.print("Leito: ");
        leito = scanner.nextLine();
        System.out.print("DIH: ");
        dih = scanner.nextLine();
        System.out.print("Diagnóstico: ");
        diagnostico = scanner.nextLine();
        System.out.print("Idade: ");
        idade = scanner.nextInt();
        scanner.nextLine();  // Consumir a quebra de linha
        System.out.print("Data da Avaliação: ");
        dataAvaliacao = scanner.nextLine();

        System.out.println("\n=== Sinais Vitais Aferidos ===");
        System.out.print("Frequência Cardíaca por minuto: ");
        freqCardiaca = scanner.nextInt();
        System.out.print("Frequência Respiratória por minuto: ");
        freqRespiratoria = scanner.nextInt();

        System.out.println("\n=== Componentes Avaliativos ===");
        System.out.print("Avaliação Neurológica (0-3): ");
        avaliacaoNeurologica = scanner.nextInt();
        System.out.print("Avaliação Cardiovascular (0-3): ");
        avaliacaoCardiovascular = scanner.nextInt();
        System.out.print("Avaliação Respiratória (0-3): ");
        avaliacaoRespiratoria = scanner.nextInt();

        System.out.println("\n=== Pontuação Extra ===");
        System.out.print("Nebulização de resgate nos últimos 15 minutos (0 ou 1): ");
        nebulizacaoResgate = scanner.nextInt();
        System.out.print("3 episódios ou mais de êmese no pós-operatório (0 ou 1): ");
        emesePosOperatorio = scanner.nextInt();

        scanner.close();
    }

    public int calcularPontuacao() {
        return avaliacaoNeurologica + avaliacaoCardiovascular + avaliacaoRespiratoria + nebulizacaoResgate + emesePosOperatorio;
    }

    public void recomendarIntervencao(int pontuacao) {
        System.out.println("\n=== Intervenção Recomendada ===");
        if (pontuacao == 0) {
            System.out.println("Manter rotina de avaliação. PEWS a cada 24 horas.");
            System.out.println("Sinais Vitais de 6/6 horas.");
        } else if (pontuacao <= 3) {
            System.out.println("Aumento da frequência de monitoramento.");
            System.out.println("Avaliação PEWS a cada 4 horas.");
        } else if (pontuacao <= 6) {
            System.out.println("Revisão clínica imediata por um médico.");
            System.out.println("Avaliação PEWS a cada 2 horas.");
        } else {
            System.out.println("Alerta máximo! Acionar equipe de emergência.");
            System.out.println("Monitoramento contínuo e possível transferência para UTI.");
        }
    }

    public void exibirResultados() {
        int pontuacaoFinal = calcularPontuacao();
        System.out.println("\n=== Resultado da Avaliação ===");
        System.out.println("Pontuação Final: " + pontuacaoFinal);
        recomendarIntervencao(pontuacaoFinal);
    }

    public static void main(String[] args) {
        BPEWS ferramenta = new BPEWS();
        ferramenta.coletarDados();
        ferramenta.exibirResultados();
    }
}
