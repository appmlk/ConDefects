X=int(input())
numbers=[]

for k in range(1,18):
    for d in range(1,10):
        for l in range(-8,10):
            number=d
            digits=[str(d)]
            for _ in range(k):
                number+=l
                digits.append(str(number))
            if 0<=int(digits[-1])<10:
                numbers.append(int(''.join(digits)))

numbers.sort()

for number in numbers:
    if number>=X:
        print(number)
        exit()