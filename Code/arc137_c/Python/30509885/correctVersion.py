def find_winner(seq):
    if (seq[-1] - seq[-2]) >= 2:
        return 'Alice'
    if (max(seq) - len(seq)) % 2 == 0:
        return 'Alice'
    else:
        return 'Bob'
    
n = int(input())
seq = [int(num) for num in input().split()]
print(find_winner(seq))