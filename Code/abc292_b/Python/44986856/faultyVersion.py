# B - Yellow and Red Card
def main():
  N, Q = map(int, input().split())
  cards = [0] * (N+1)

  for _ in range(Q):
    c, x = map(int, input().split())

    if c == 1:
      cards[x] += 1
    elif c == 2:
      cards[x] += 2
    else:
      if cards[x] == 2:
        print('Yes')
      else:
        print('No')
      

if __name__ == '__main__':
  main()