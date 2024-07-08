numbers = []
nine = set(list(i for i in range(1,10)))

def check_row(data):
    for i in data:
        if nine != set(i):
            return False
    return True

def check_col(data):
    for i in zip(*data):
        if nine != set(i):
            return False
    return True

def check_block(data):
    for i in range(0,9,3):
        for j in range(0,9,3):
            num = set()
            for row in range(3):
                for col in range(3):
                    num.add(data[row][col])
            if nine != num:
                return False
    return True

for i in range(9):
    a = list(map(int,input().split()))
    numbers.append(a)

if check_col(numbers) and check_row(numbers) and check_block(numbers):
    print("Yes")
else:
    print("No")

