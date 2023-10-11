def fatores_primos(n):
    dois = 0
    tres = 0
    while n % 2 == 0:
        dois += 1
        n //= 2
    while n % 3 == 0:
        tres += 1
        n //= 3
    return dois, tres, n



t = int(input())

entrada = list(map(int, input().split()))



lista = []
for e in entrada:
    lista.append(fatores_primos(e))

menor_x = float('inf')
menor_y = float('inf')
r = lista[0][2]

v = 1
for tupla in lista:
    if tupla[2] != r:
        v = 0
        break

    if tupla[0] < menor_x:
        menor_x = tupla[0]

    if tupla[1] < menor_y:
        menor_y = tupla[1]

if not v:
    print(-1)
else:
    cont = 0
    for tupla in lista:
        if tupla[0] > menor_x:
            cont += tupla[0] - menor_x
        if tupla[1] > menor_y:
            cont += tupla[1] - menor_y

    print(cont)