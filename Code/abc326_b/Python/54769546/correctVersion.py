N = int(input())
for i in range(N, 920):
    N_str = str(i)
    if int(N_str[0]) * int(N_str[1]) == int(N_str[2]):
        print(i)
        exit()