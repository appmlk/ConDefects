def solve_C():
    n, m = map(int, input().split())
    s = [int(day) for day in list(input())]

    logo = 0
    plain = m
    buy = 0
    for day in s:
        if day == 1:
            if plain > 0:
                plain -= 1
            else:
                buy += 1
        if day == 2:
            buy += 1
        if day == 0:
            logo = max(logo, buy)
            buy = 0
            plain = m
    
    print(max(logo, buy))

solve_C()
