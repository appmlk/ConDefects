def resolve():
    B = int(input())
    for x in range(1, B+1):
        tmp = x**x
        if tmp==B:
            return x
        elif tmp>B:
            return -1
print(resolve())