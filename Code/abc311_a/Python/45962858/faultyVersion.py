n = int(input())
result = [char for char in input()]

flag_a = 0
flag_b = 0
flag_c = 0

for i in range(n):
    if result[i] == 'A':
        flag_a = 1
    elif result[i] == 'B':
        flag_b = 1
    elif result[i] == 'C':
        flag_c = 1

    if flag_a + flag_b + flag_c == 3:
        print(i)
        break