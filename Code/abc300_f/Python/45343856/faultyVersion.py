from itertools import accumulate

class RepeatingSequence:
    def __init__(self, head, cycle, tail, repeat):
        self.head = head
        self.cycle = cycle
        self.tail = tail
        self.repeat = repeat
        self.head_cumsums = None
        self.cycle_cumsums = None
        self.tail_cumsums = None
    
    def __len__(self):
        return len(self.head) + len(self.cycle) * self.repeat + len(self.tail)

    def sum(self, l, r):
        return self.cumsum(r) - self.cumsum(l)

    def cumsum(self, r):
        if self.head_cumsums is None:
            self.calc_cumsums()
        
        if r <= len(self.head):
            return self.head_cumsums[r]
        
        sum_ = self.head_cumsums[-1]
        r -= len(self.head)
        if r <= len(self.cycle) * self.repeat:
            return sum_ + self.cycle_cumsums[-1] * (r // len(self.cycle)) + self.cycle_cumsums[r % len(self.cycle)]
        
        sum_ += self.cycle_cumsums[-1] * self.repeat
        r -= len(self.cycle) * self.repeat
        if r <= len(self.tail):
            return sum_ + self.tail_cumsums[r]
        
        return sum_ + self.tail_cumsums[-1]

    def calc_cumsums(self):
        self.head_cumsums = list(accumulate(self.head, initial=0))
        self.cycle_cumsums = list(accumulate(self.cycle, initial=0))
        self.tail_cumsums = list(accumulate(self.tail, initial=0))

N, M, K = map(int, input().split())
S = input()

seq = [len(s) for s in S.split("x")]
x_count = S.count("x")

if M == 1:
    rep_seq = RepeatingSequence(seq, [], [], 0)
else:
    seq1 = seq[:-1]
    seq2 = [seq[-1] + seq[0]] + seq[1:-1]
    seq3 = [seq[-1] + seq[0]] + seq[1:]
    rep_seq = RepeatingSequence(seq1, seq2, seq3, M-2)

ans = 0
for i in range(x_count):
    if i + K + 1 > len(rep_seq): break
    count = rep_seq.sum(i, i + K + 1) + K
    ans = max(ans, count)

print(ans)
