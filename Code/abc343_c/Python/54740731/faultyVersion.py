N = int(input())
least_number = 0
for number in range(1000000):
    if number**3 <= N:
        for_number = str(number**3)
        rev_number = str(number**3)[::-1]
        for keta in range(int(len(for_number))):
            if for_number[keta] != rev_number[keta]:
                break
            elif keta == int(len(str(number**3))-1):
                least_number = number**3
    else:
        print(least_number)
        break